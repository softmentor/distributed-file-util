package com.softmentor.dfs.s3.impl;

import java.io.File;
import java.util.List;

import javax.validation.constraints.Size;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.validator.constraints.NotBlank;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.ServiceException;
import org.jets3t.service.StorageObjectsChunk;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.model.StorageObject;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.softmentor.dfs.core.DistributedFileSystem;
import com.softmentor.dfs.core.exception.ConfigurationException;
import com.softmentor.dfs.core.exception.ConnectionException;
import com.softmentor.dfs.core.exception.CoreException;
import com.softmentor.dfs.core.model.dir.DDirectory;
import com.softmentor.dfs.core.model.file.DFile;
import com.softmentor.dfs.s3.S3NativeFileSystem;
import com.softmentor.dfs.s3.config.S3Configuration;
import com.softmentor.dfs.s3.provider.JetS3TProvider;


/**
 * This is a specific implementation against amazon s3 file service/system. Details Refer : http://aws.amazon.com/s3/
 * 
 * @author jiny
 * 
 */
@Slf4j
public class S3FileSystemImpl<S3> implements DistributedFileSystem<S3>, S3NativeFileSystem<S3>
{

    // private S3Service s3Service;

    private JetS3TProvider  svcProvider;

    private S3Configuration config;

    public S3FileSystemImpl(final S3Configuration conf) throws ConfigurationException
    {
        this.svcProvider = new JetS3TProvider(conf);
        this.config = conf;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softmentor.dfs.s3.S3NativeFileSystem#createBucket(java.lang.String)
     */
    @Override
    public void createBucket(String bucketName) throws CoreException, ConnectionException
    {

        // TODO Auto-generated method stub
        try
        {
            svcProvider.getS3Service().createBucket(bucketName, config.getRegionName());
        }
        catch (S3ServiceException e)
        {
            throw new CoreException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softmentor.dfs.s3.S3NativeFileSystem#getOrCreateBucket(java.lang.String)
     */
    @Override
    public void getOrCreateBucket(String bucketName) throws CoreException, ConnectionException
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softmentor.dfs.core.DistributedFileSystem#createDirectory(com.softmentor.dfs.core.model.dir.DDirectory)
     */
    @Override
    public void createDirectory(DDirectory<S3> dir) throws CoreException, ConnectionException
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softmentor.dfs.core.DistributedFileSystem#removeDirectory(com.softmentor.dfs.core.model.dir.DDirectory)
     */
    @Override
    public void removeDirectory(DDirectory<S3> dir) throws CoreException, ConnectionException
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softmentor.dfs.core.DistributedFileSystem#updateDirectory(com.softmentor.dfs.core.model.dir.DDirectory)
     */
    @Override
    public void updateDirectory(DDirectory<S3> dir) throws CoreException, ConnectionException
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softmentor.dfs.core.DistributedFileSystem#viewDirectory(com.softmentor.dfs.core.model.dir.DDirectory)
     */
    @Override
    public DDirectory<S3> viewDirectory(DDirectory<S3> dir) throws CoreException, ConnectionException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softmentor.dfs.core.DistributedFileSystem#listDirectory(com.softmentor.dfs.core.model.dir.DDirectory)
     */
    @Override
    public List<DFile<S3>> listDirectory(DDirectory<S3> dir) throws CoreException, ConnectionException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softmentor.dfs.core.DistributedFileSystem#uploadDirectory(java.io.File,
     * com.softmentor.dfs.core.model.dir.DDirectory, boolean)
     */
    @Override
    public void uploadDirectory(File fromLocalDir, DDirectory<S3> toDfsDir, boolean isRecursive) throws CoreException,
            ConnectionException
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softmentor.dfs.core.DistributedFileSystem#uploadFiles(File[] files, String toLocationName)
     */
    @Override
    @Metered
    @Timed
    public void uploadFiles(@Size(min = 1) java.io.File[] files, @NotBlank String toLocationName) throws CoreException,
            ConnectionException
    {

        final String name = "SimpleFile_Upload";

        S3Object[] s3ObjectArray = new S3Object[files.length];
        int count = 0;
        for (File file : files)
        {
            if (file != null && file.isFile())
            {
                S3Object s3Object = new S3Object();
                s3Object.setDataInputFile(file);
                s3Object.setAcl(AccessControlList.REST_CANNED_AUTHENTICATED_READ);
                s3Object.setName(file.getName());
                s3Object.setKey(file.getName());
                s3Object.setContentLength(file.length());
                s3ObjectArray[count] = s3Object;
                count++;
            }
        }

        if (count > 0)
        {
            try
            {
                svcProvider.getS3ThreadedService().putObjects(toLocationName, s3ObjectArray);
            }
            catch (ServiceException e)
            {
                throw new CoreException(e);
            }
        }
        log.debug("Files found count= {} uploaded to location= {}", count, toLocationName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softmentor.dfs.core.DistributedFileSystem#downloadDirectory(com.softmentor.dfs.core.model.dir.DDirectory,
     * java.io.File, boolean)
     */
    @Override
    public void downloadDirectory(DDirectory<S3> fromDfsdir, File toLocalDir, boolean isRecursive)
            throws CoreException, ConnectionException
    {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softmentor.dfs.core.DistributedFileSystem#downloadDirectory(com.softmentor.dfs.core.model.dir.DDirectory,
     * java.io.File, boolean)
     */
    @Override
    @Metered
    @Timed
    public void downloadFiles(@NotBlank String fromLocationName, String prefix) throws CoreException,
            ConnectionException
    {
        log.debug("Downloading from S3 bucket {} and using the prefix :{} to search the files", fromLocationName,
            prefix);
        StorageObject[] objs;

        try
        {
            StorageObjectsChunk chunk = svcProvider.getS3Service().listObjectsChunked(fromLocationName, prefix, null,
                0, null, true);
            objs = chunk.getObjects();

        }
        catch (ServiceException e)
        {
            log.error("Exception while downloading files", e);
            throw new CoreException(e);
        }
        log.debug("Files found count= {} download from location= {}", objs.length, fromLocationName);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softmentor.dfs.core.DistributedFileSystem#createFile(com.softmentor.dfs.core.model.file.DFile)
     */
    @Override
    public void createFile(DFile<S3> file) throws CoreException, ConnectionException
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softmentor.dfs.core.DistributedFileSystem#removeFile(com.softmentor.dfs.core.model.file.DFile)
     */
    @Override
    public void removeFile(DFile<S3> file) throws CoreException, ConnectionException
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softmentor.dfs.core.DistributedFileSystem#updateFile(com.softmentor.dfs.core.model.file.DFile)
     */
    @Override
    public void updateFile(DFile<S3> file) throws CoreException, ConnectionException
    {
        // TODO Auto-generated method stub

    }

}

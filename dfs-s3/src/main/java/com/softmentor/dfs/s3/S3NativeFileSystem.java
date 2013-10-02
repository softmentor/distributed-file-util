package com.softmentor.dfs.s3;

import com.softmentor.dfs.core.exception.ConnectionException;
import com.softmentor.dfs.core.exception.CoreException;



/**
 * This implementation is based on AWS S3
 * 
 * References:
 * 1) Some examples are available here: https://github.com/awslabs/aws-java-sample
 * 2) API docs: http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/welcome.html
 * 
 * @author jiny
 *
 */
public interface S3NativeFileSystem<T>
{
    void createBucket(String bucketName) throws CoreException, ConnectionException;
    
    void getOrCreateBucket(String bucketName) throws CoreException, ConnectionException;
    
    

}

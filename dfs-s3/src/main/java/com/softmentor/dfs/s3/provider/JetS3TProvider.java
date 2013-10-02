package com.softmentor.dfs.s3.provider;

import javax.validation.Valid;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.multi.SimpleThreadedStorageService;
import org.jets3t.service.security.AWSCredentials;

import com.softmentor.dfs.core.exception.ConfigurationException;
import com.softmentor.dfs.s3.config.S3Configuration;


/**
 * @author jiny
 * @param <T>
 * 
 */
@Slf4j
@Getter
public class JetS3TProvider
{
    private S3Configuration              config;

    private S3Service                    s3Service;

    private SimpleThreadedStorageService s3ThreadedService;

    public JetS3TProvider(@Valid S3Configuration config) throws ConfigurationException
    {
        this.config = config;
        init();
    }

    private void init() throws ConfigurationException
    {
        try
        {
            AWSCredentials credentials = new AWSCredentials(
                    config.getCoreConfig().getConnectionConfig().getAccesskey(), config
                            .getCoreConfig()
                                .getConnectionConfig()
                                .getSecretkey());
            s3Service = new RestS3Service(credentials);
            s3ThreadedService = new SimpleThreadedStorageService(s3Service);
        }
        catch (S3ServiceException e)
        {
            log.error("S3 Initialization error ", e);
            throw new ConfigurationException(e);
        }
    }

}

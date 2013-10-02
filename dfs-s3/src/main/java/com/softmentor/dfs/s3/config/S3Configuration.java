package com.softmentor.dfs.s3.config;

import lombok.Data;

import com.softmentor.dfs.core.config.CoreConfiguration;


/**
 * @author jiny
 * 
 */
@Data
public class S3Configuration
{
    private CoreConfiguration coreConfig;
    
    private String bucketName = "defaultBucket";
    
    private String regionName = "US";

}

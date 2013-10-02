package com.softmentor.dfs.core.config;

import java.io.File;
import java.util.Properties;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * @author jiny
 * 
 */
@Builder
@Data
public class CoreConfiguration
{
    @NotNull
    private ConnectionConfiguration connectionConfig;
    
    private Properties configProps;
    
    private File configFile;

}

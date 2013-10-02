package com.softmentor.dfs.core.model.file;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

import com.softmentor.dfs.core.model.DFileSystem;

/**
 * @author jiny
 * 
 */
@Builder(chain=true)
@EqualsAndHashCode
public class DFile<T> extends DFileSystem
{
    @NotNull
    private DFileContext context;

}
     
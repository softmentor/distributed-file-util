package com.softmentor.dfs.core.model.dir;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

import com.softmentor.dfs.core.model.DFileSystem;
import com.softmentor.dfs.core.model.file.DFile;


/**
 * @author jiny
 * 
 */
@Builder(chain = true)
@EqualsAndHashCode
public class DDirectory<T> extends DFileSystem
{
    @NotNull
    private DDirContext context;
    
    private List<DFile<T>> files;

}

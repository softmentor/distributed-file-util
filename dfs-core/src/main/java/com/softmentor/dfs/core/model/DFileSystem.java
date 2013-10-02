package com.softmentor.dfs.core.model;

import java.util.Map;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/**
 * This is simple pojo to hold the attributes of a distributed file system item like directory, file etc.
 * 
 * <p>
 * The constraint on the name Regex:
 * <li>names can contain any combination of uppercase letters, lowercase letters, numbers, periods (.), dashes (-) and
 * underscores (_)
 * <li>names can only begin with letters, dashes (-) and underscores (_)
 * 
 * 
 * <p>
 * <b>Details:</b>
 * <li>^ asserts that the regular expression must match at the beginning of the subject
 * <li>[] is a character class - any character that matches inside this expression is allowed
 * <li>A-Z allows a range of uppercase characters
 * <li>a-z allows a range of lowercase characters
 * <li>. matches a period
 * <li>\- matches a dash (hyphen); we escape it to let the engine know we want to match a dash rather than a range of
 * characters
 * <li>_ matches an underscore
 * <li>\d matches only digits, same as 0-9
 * <li>\w matches any word character
 * <li>\s matches whitespace (spaces and tabs)
 * <li>+ asserts that the preceding expression (in our case, the character class) must match one or more times
 * <li>$ Finally, this asserts that we're now at the end of the subject
 * 
 * <li>Use: http://regexpal.com/ to test expressions.
 * <p>
 * <b>Note:</b> When specifying string in java you would have escape \, hence \d regex would become \\d.
 * 
 * @author jiny
 * 
 */
@Getter
@Setter
public class DFileSystem
{
    @NotBlank
    @Length(min = 1, max = 255)
    @Pattern(regexp = "^[a-zA-Z_-][\\d\\w.\\-_\\s]+$")
    private String              name;

    private String              ownerId;

    private String              ownerDisplayName;

    private Map<String, Object> metaData;

}

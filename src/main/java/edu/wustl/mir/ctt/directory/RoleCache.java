package edu.wustl.mir.ctt.directory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Simple cache for role names associated with usernames.
 *
 */
public class RoleCache {
    private Map<String, Set<String>> cache = new HashMap<>();

    public boolean hasRolesFor( String userName) {
        return cache.containsKey( userName);
    }
    public void putRoles( String userName, Set<String> roleNames) {
        cache.put( userName, roleNames);
    }

    public Set<String> getRolesFor( String userName) {
        return cache.get(userName);
    }
}


package com.ecc.app;

public interface RoleService {
	public boolean createRole();
	public void readRole(Role role);
	public void readRoles();
	public boolean updateRole();
	public boolean deleteRole(); 
}
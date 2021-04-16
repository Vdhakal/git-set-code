package com.example.git_set_code.networkUtil;

public interface EntityMapper<Entity, DomainModel> {
    public DomainModel mapFromEntity(Entity entity);
    public Entity mapToEntity(DomainModel domainModel);
}

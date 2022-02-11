package ua.com.alevel.persistence.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.persistence.entity.AbstractEntity;

import javax.persistence.*;

public class AuditTrailListener {

    private static final Logger LOG = LoggerFactory.getLogger(AuditTrailListener.class);

    @PrePersist
    private void beforeSave(AbstractEntity entity) {
        LOG.debug("{}: Attempt to save with uuid: {}", entity.getClass().getSimpleName(), entity.getUuid());
    }

    @PreUpdate
    private void beforeUpdate(AbstractEntity entity) {
        LOG.debug("{}: Attempt to update with uuid: {}", entity.getClass().getSimpleName(), entity.getUuid());
    }

    @PreRemove
    private void beforeDelete(AbstractEntity entity) {
        LOG.debug("{}: Attempt to delete with uuid: {}", entity.getClass().getSimpleName(), entity.getUuid());
    }

    @PostPersist
    private void afterSave(AbstractEntity entity) {
        LOG.debug("{}: was saved successfully with uuid: {}", entity.getClass().getSimpleName(), entity.getUuid());
    }

    @PostUpdate
    private void afterUpdate(AbstractEntity entity) {
        LOG.debug("{}: was updated successfully with uuid: {}", entity.getClass().getSimpleName(), entity.getUuid());
    }

    @PostRemove
    private void afterDelete(AbstractEntity entity) {
        LOG.debug("{}: was deleted successfully with uuid: {}", entity.getClass().getSimpleName(), entity.getUuid());
    }

    @PostLoad
    private void afterLoad(AbstractEntity entity) {
        LOG.debug("{}: getting by uuid: {}", entity.getClass().getSimpleName(), entity.getUuid());
    }
}

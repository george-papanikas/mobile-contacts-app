package gr.aueb.mobileapp.model;

@FunctionalInterface
public interface IdentifiableEntity {

    /**
     * Returns the id of the entity.
     *
     * @return the entity id.
     */
    long getId();
}

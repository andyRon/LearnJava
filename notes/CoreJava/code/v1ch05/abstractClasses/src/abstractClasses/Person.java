package abstractClasses;

/**
 * @author Andy Ron
 */
public abstract class Person {
    /**
     *
     * @return
     */
    public abstract String getDescription();

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

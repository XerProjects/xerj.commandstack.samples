package sample.springboot.commands;

public class UnhandledCommand {
    private int id;
    private String name;

    public UnhandledCommand(int id, String name) {
        this.setId(id);
        this.setName(name);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}

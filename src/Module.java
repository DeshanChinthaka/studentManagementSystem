public class Module {      // create a module class
    private final String moduleName;     // set final variable to module name
    private int moduleMarks;     // set a variable to module marks

    //constructor to initialize module name
    public Module(String moduleName, int moduleMarks) {
        this.moduleName = moduleName;
        this.moduleMarks = moduleMarks;
    }

    // Getter method to return the module name
    public String getModuleName() {
        return moduleName;
    }

    // Getter method to return the module mark
    public int getModuleMarks() {
        return moduleMarks;
    }

    // Setter method to set the module marks
    public void setModuleMarks(int moduleMarks) {
        this.moduleMarks = moduleMarks;
    }
}

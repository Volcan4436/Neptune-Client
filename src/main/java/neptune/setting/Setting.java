package neptune.setting;

public class Setting {
    private VisibilityDependency dependency = null;

    private String name;
    private boolean visible = true;

    public Setting(String name) {
        this.name = name;
    }

    public boolean isVisible() {
        if (dependency != null) return dependency.isVisible();
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setDependency(VisibilityDependency dependency) {
        this.dependency = dependency;
    }

    public String getName() {
        return name;
    }

}

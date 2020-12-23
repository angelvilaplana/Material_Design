package orihuel.vilaplana.angel.materialdesign;

/**
 * Languages used in the application
 */
public enum  OperatedLanguage {
    ENGLISH("English", R.drawable.ic_united_kingdom),
    SPANISH("Español", R.drawable.ic_spain),
    CATALAN("Català", R.drawable.ic_catalonia),
    FRENCH("Français", R.drawable.ic_france);

    private final String language;
    private final int icon;

    OperatedLanguage(String language, int icon) {
        this.language = language;
        this.icon = icon;
    }

    public String getName() {
        return language;
    }

    public int getIcon() {
        return icon;
    }

}

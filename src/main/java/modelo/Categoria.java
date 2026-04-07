package main.java.modelo;

public enum Categoria {
    //gastos
    COMIDA("🍕 comida"),
    TRANSPORTE("🚗 transporte"),
    HOGAR("🏠 hogar"),
    SERVICIOS("💡 servicios"),
    SALUD("🩺 salud"),
    SALIDAS("🎟 salidas"),
    EDUCACION("🎓 educación"),
    ROPAYZAPATOS("🧦 ropa y zapatos"),
    OTROSGASTOS("📦 otros gastos"),

    //ingresos
    SALARIO("💰 salario"),
    CACHUELO("💻 cachuelo"),
    INVERSIONES("📈 inversiones"),
    OTROSINGRESOS("✨ otros ingresos");

    private final String emoji;

    Categoria(String emoji){
        this.emoji = emoji;
    }

    public String getEmoji(){
        return emoji;
    }
}

package utils;

public class ArmazemInt {
    private int value;

    private static final ArmazemInt INSTANCE = new ArmazemInt();

    // O construtor é privado para que a classe não possa ser instanciada de fora.
    private ArmazemInt() {
        // Coloque aqui a lógica de inicialização, se houver.
    }

    // Método público estático que é o único meio global de obter a instância única.
    public static ArmazemInt getInstance() {
        return INSTANCE;
    }

    public void setValue(int v){
        this.value = v;
    }

    public int getValue(){
        return this.value;
    }
}

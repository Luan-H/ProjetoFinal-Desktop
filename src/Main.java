import br.com.Agenda.view.TelaLogin;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Executa a interface na Thread correta do Swing
        SwingUtilities.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}
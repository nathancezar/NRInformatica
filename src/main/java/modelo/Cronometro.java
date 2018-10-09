package modelo;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author nathancezar
 */
public class Cronometro {

    private Timer timer;
    private TimerTask tarefa;
    private int tempoMaximoParaCarrinho = 1;
    private boolean cronometroRodando;
    private final Carrinho carrinho;

    public Cronometro(Carrinho carrinho) {
        this.carrinho = carrinho;
        this.timer = new Timer();
        this.cronometroRodando = false;
    }

    public boolean isCronometroRodando() {
        return cronometroRodando;
    }

    private TimerTask novaTarefa(Carrinho carrinho) {
        tarefa = new TimerTask() {
            @Override
            public void run() {
                carrinho.removerTodosOsProdutos();
                JOptionPane.showMessageDialog(null, "Tempo do carrinho excedido!");
            }
        };
        return tarefa;
    }

    public void run() {
        if (!cronometroRodando) {
            tarefa = novaTarefa(carrinho);
            timer.schedule(tarefa, tempoMaximoParaCarrinho * 60000);
            cronometroRodando = true;
        } else {
            tarefa.cancel();
            timer.cancel();
            timer.purge();
            tarefa = novaTarefa(this.carrinho);
            timer = new Timer();
            timer.schedule(tarefa, tempoMaximoParaCarrinho * 1000);
        }
    }

    public void setTempoMaximoParaCarrinho(int tempoMaximoParaCarrinho) {
        this.tempoMaximoParaCarrinho = tempoMaximoParaCarrinho;
    }
}


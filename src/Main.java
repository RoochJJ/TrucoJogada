public class JogadorTrucoAlternativo extends Jogador {

    public JogadorTrucoAlternativo(String nome) {
        super(nome);
    }

    @Override
    public Carta jogada(Carta cartaNaMesa) {
        // Se não há carta na mesa, joga a carta do meio (valor médio)
        if (cartaNaMesa == null) {
            return jogarCartaMedia();
        }

        // Se a carta na mesa for muito alta (acima de 10), joga a carta mais baixa
        if (cartaNaMesa.valor > 10) {
            return jogarMenorCarta();
        }

        // Se a carta na mesa for muito baixa (abaixo de 5), joga a carta mais alta
        if (cartaNaMesa.valor < 5) {
            return jogarMaiorCarta();
        }

        // Se a carta na mesa tiver um valor intermediário, joga uma carta intermediária
        return jogarCartaIntermediaria(cartaNaMesa.valor);
    }

    private Carta jogarCartaMedia() {
        if (cartas.isEmpty()) return null;
        int totalValor = 0;
        for (Carta carta : cartas) {
            totalValor += carta.valor;
        }
        int valorMedio = totalValor / cartas.size();
        Carta cartaMedia = cartas.get(0);
        int diferencaMinima = Math.abs(cartaMedia.valor - valorMedio);
        for (Carta carta : cartas) {
            int diferencaAtual = Math.abs(carta.valor - valorMedio);
            if (diferencaAtual < diferencaMinima) {
                cartaMedia = carta;
                diferencaMinima = diferencaAtual;
            }
        }
        cartas.remove(cartaMedia);
        return cartaMedia;
    }

    private Carta jogarCartaIntermediaria(int valorReferencia) {
        if (cartas.isEmpty()) return null;
        Carta cartaIntermediaria = cartas.get(0);
        int diferencaMinima = Math.abs(cartaIntermediaria.valor - valorReferencia);
        for (Carta carta : cartas) {
            int diferencaAtual = Math.abs(carta.valor - valorReferencia);
            if (diferencaAtual < diferencaMinima) {
                cartaIntermediaria = carta;
                diferencaMinima = diferencaAtual;
            }
        }
        cartas.remove(cartaIntermediaria);
        return cartaIntermediaria;
    }

    private Carta jogarMaiorCarta() {
        if (cartas.isEmpty()) return null;
        Carta maiorCarta = cartas.get(0);
        for (Carta carta : cartas) {
            if (carta.valor > maiorCarta.valor) {
                maiorCarta = carta;
            }
        }
        cartas.remove(maiorCarta);
        return maiorCarta;
    }

    private Carta jogarMenorCarta() {
        if (cartas.isEmpty()) return null;
        Carta menorCarta = cartas.get(0);
        for (Carta carta : cartas) {
            if (carta.valor < menorCarta.valor) {
                menorCarta = carta;
            }
        }
        cartas.remove(menorCarta);
        return menorCarta;
    }

    private Carta jogarCarta(Carta carta) {
        cartas.remove(carta);
        return carta;
    }
}

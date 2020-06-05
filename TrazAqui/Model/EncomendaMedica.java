package Model;

import java.awt.geom.Point2D;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class EncomendaMedica extends Encomenda {

    public EncomendaMedica() {
        super();
    }

    public EncomendaMedica(User comprador, Transporte distribuidor, Loja loja, Point2D.Double moradaOrigem, Point2D.Double moradaDestino, String referencia, float peso, LocalDateTime date, Duration tempo, List<Produto> lst, double custo, double custoviagem ,boolean efetuada) {
        super(comprador, distribuidor, loja, moradaOrigem, moradaDestino, referencia, peso, date, tempo, lst, custo, custoviagem, efetuada);
    }

    public EncomendaMedica(Encomenda a) {
        super(a);
    }
}

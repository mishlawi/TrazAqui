package Model;

import java.awt.geom.Point2D;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class EncomendaMedica extends Encomenda {

    public EncomendaMedica() {
        super();
    }

    public EncomendaMedica(User comprador, Transporte distribuidor, Loja loja, Point2D.Double moradaOrigem, Point2D.Double moradaDestino, String referencia, float peso, LocalDateTime date, Double tempo, List<Produto> lst, double custo, double custoviagem, boolean aceite, boolean efetuada, boolean aceite2) {
        super(comprador, distribuidor, loja, referencia, peso, date, tempo, lst, custo, custoviagem,aceite, efetuada,aceite2);
    }

    public EncomendaMedica(Encomenda a) {
        super(a);
    }
}

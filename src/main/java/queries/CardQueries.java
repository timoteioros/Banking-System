package queries;

public class CardQueries {

    public static final String DELETE_CARD = "DELETE FROM Card c WHERE c.id_card = :id_card";
    public static final String CARD_BY_ID = "Select c From Card c WHERE c.id_card = :id_card";
}

package ms.saga;


import org.assertj.core.api.Assertions;
import org.junit.Test;

public class StatusTest {

    @Test
    public void statusTest1() {

        Status sut = new Status();

        Assertions.assertThat( sut.isFullyCommitted() ).isEqualTo( false );

        sut.committed(Resources.CREDIT_CARD);
        Assertions.assertThat( sut.isFullyCommitted() ).isEqualTo( false );

        sut.committed(Resources.SMS_NOTIFICATION);
        Assertions.assertThat( sut.isFullyCommitted() ).isEqualTo( false );

        sut.committed(Resources.INVENTORY);
        Assertions.assertThat( sut.isFullyCommitted() ).isEqualTo( true );

    }

    @Test
    public void statusTest2() {

        Status sut = new Status();

        Assertions.assertThat( sut.isFullyCommitted() ).isEqualTo( false );

        sut.committed(Resources.SMS_NOTIFICATION);
        Assertions.assertThat( sut.isFullyCommitted() ).isEqualTo( false );

        sut.committed(Resources.INVENTORY);
        Assertions.assertThat( sut.isFullyCommitted() ).isEqualTo( false );

        sut.committed(Resources.CREDIT_CARD);
        Assertions.assertThat( sut.isFullyCommitted() ).isEqualTo( true );

    }

    @Test
    public void statusTest10() {

        Status sut = new Status();

        Assertions.assertThat( sut.isCompletedlyRolledBack() ).isEqualTo( true );

        sut.committed(Resources.SMS_NOTIFICATION);
        sut.rollback(Resources.SMS_NOTIFICATION);
        Assertions.assertThat( sut.isCompletedlyRolledBack() ).isEqualTo( true );

        sut.committed(Resources.SMS_NOTIFICATION);
        sut.committed(Resources.CREDIT_CARD);
        sut.rollback(Resources.SMS_NOTIFICATION);
        sut.rollback(Resources.CREDIT_CARD);
        Assertions.assertThat( sut.isCompletedlyRolledBack() ).isEqualTo( true );


    }

    @Test
    public void statusTest11() {

        Status sut = new Status();

        sut.committed(Resources.SMS_NOTIFICATION);
        sut.committed(Resources.CREDIT_CARD);
        sut.rollback(Resources.SMS_NOTIFICATION);
        Assertions.assertThat( sut.isCompletedlyRolledBack() ).isEqualTo( false );


    }

    @Test(expected = IllegalStateException.class)
    public void statusTest3() {

        Status sut = new Status();

        sut.committed(Resources.SMS_NOTIFICATION);
        sut.committed(Resources.SMS_NOTIFICATION);

    }

    @Test(expected = IllegalStateException.class)
    public void statusTest4() {

        Status sut = new Status();

        sut.rollback(Resources.CREDIT_CARD);
        sut.rollback(Resources.CREDIT_CARD);

    }

}

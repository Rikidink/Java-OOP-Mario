package game;

public class Wallet {

    private Integer walletValue;

    /**
     * A singleton wallet instance
     */
    private static Wallet instance;

    /**
     * Get the singleton instance of wallet
     * @return Wallet singleton instance
     */
    public static Wallet getInstance(){
        if(instance == null){
            instance = new Wallet();
        }
        return instance;
    }

    public Wallet () {
        walletValue = 0;
    }

    public void updateWallet (Integer value) {
        walletValue += value;
    }

    public Integer getWalletValue() {
        return walletValue;
    }
}

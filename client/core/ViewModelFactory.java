package GameApp.client.core;

import GameApp.client.views.AdminAddGameView.AdminAddGameViewModel;
import GameApp.client.views.AdminMainShopView.AdminMainShopViewModel;
import GameApp.client.views.AdminMyAccountView.AdminMyAccountViewModel;
import GameApp.client.views.AdminTransactionHistoryView.AdminTransactionHistoryViewModel;
import GameApp.client.views.AdminUserListView.AdminUserListViewModel;
import GameApp.client.views.GameView.GameViewModel;
import GameApp.client.views.LoginView.LoginViewModel;
import GameApp.client.views.MainShopView.MainShopViewModel;
import GameApp.client.views.MyAccountView.MyAccountViewModel;
import GameApp.client.views.MyLibraryView.MyLibraryViewModel;
import GameApp.client.views.PaymentView.PaymentViewModel;
import GameApp.client.views.RegisterView.RegisterViewModel;
import GameApp.client.views.ShoppingCartView.ShoppingCartViewModel;
import GameApp.client.views.UserTransactionHistoryView.UserTransactionHistoryViewModel;

/**
 * A Class returns single instance of view models.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class ViewModelFactory {
    private static ViewModelFactory instance = new ViewModelFactory();

    private LoginViewModel loginViewModel;
    private RegisterViewModel registerViewModel;
    private MainShopViewModel mainShopViewModel;
    private MyAccountViewModel myAccountViewModel;
    private MyLibraryViewModel myLibraryViewModel;
    private GameViewModel gameViewModel;
    private AdminMainShopViewModel adminMainShopViewModel;
    private AdminUserListViewModel adminUserListViewModel;
    private ShoppingCartViewModel shoppingCartViewModel;
    private PaymentViewModel paymentViewModel;
    private AdminTransactionHistoryViewModel adminTransactionHistoryViewModel;
    private AdminMyAccountViewModel adminMyAccountViewModel;
    private AdminAddGameViewModel adminAddGameViewModel;
    private UserTransactionHistoryViewModel userTransactionHistoryViewModel;

    /**
     * Gets instance.
     * @return ViewModelFactory instance
     */
    public static ViewModelFactory getInstance() {
        return instance;
    }

    /**
     * 0 argument constructor.
     */
    public ViewModelFactory() {
    }

    /**
     * Gets single login viewModel.
     * @return LoginViewModel object
     */
    public LoginViewModel getLoginViewModel() {
        if (loginViewModel == null)
            loginViewModel = new LoginViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return loginViewModel;
    }

    /**
     * Gets single register viewModel.
     * @return RegisterViewModel object
     */
    public RegisterViewModel getRegisterViewModel() {
        if (registerViewModel == null)
            registerViewModel = new RegisterViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return registerViewModel;
    }

    /**
     * Gets single main shop viewModel.
     * @return MainShopViewModel object
     */
    public MainShopViewModel getMainShopViewModel() {
        if (mainShopViewModel == null)
            mainShopViewModel = new MainShopViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return mainShopViewModel;
    }

    /**
     * Gets single my account  viewModel.
     * @return MyAccountViewModel object
     */
    public MyAccountViewModel getMyAccountViewModel() {
        if (myAccountViewModel == null)
            myAccountViewModel = new MyAccountViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return myAccountViewModel;
    }

    /**
     * Gets single my library viewModel.
     * @return MyLibraryViewModel object
     */
    public MyLibraryViewModel getMyLibraryViewModel() {
        if (myLibraryViewModel == null)
            myLibraryViewModel = new MyLibraryViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return myLibraryViewModel;
    }

    /**
     * Gets single game viewModel.
     * @return GameViewModel object
     */
    public GameViewModel getGameViewModel() {
        if (gameViewModel == null)
            gameViewModel = new GameViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return gameViewModel;
    }

    /**
     * Gets single admin main shop viewModel.
     * @return AdminMainShopViewModel object
     */
    public AdminMainShopViewModel getAdminMainShopViewModel() {
        if (adminMainShopViewModel == null)
            adminMainShopViewModel = new AdminMainShopViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return adminMainShopViewModel;
    }

    /**
     * Gets single admin user list viewModel.
     * @return AdminUserListViewModel object
     */
    public AdminUserListViewModel getAdminUserListViewModel() {
        if (adminUserListViewModel == null)
            adminUserListViewModel = new AdminUserListViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return adminUserListViewModel;
    }

    /**
     * Gets single shopping cart viewModel.
     * @return ShoppingCartViewModel object
     */
    public ShoppingCartViewModel getShoppingCartViewModel() {
        if (shoppingCartViewModel == null)
            shoppingCartViewModel = new ShoppingCartViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return shoppingCartViewModel;
    }

    /**
     * Gets single payment viewModel.
     * @return PaymentViewModel object
     */
    public PaymentViewModel getPaymentViewModel() {
        if (paymentViewModel == null)
            paymentViewModel = new PaymentViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return paymentViewModel;
    }

    /**
     * Gets single admin transaction history viewModel.
     * @return AdminTransactionHistoryViewModel object
     */
    public AdminTransactionHistoryViewModel getAdminTransactionHistoryViewModel() {
        if (adminTransactionHistoryViewModel == null)
            adminTransactionHistoryViewModel = new AdminTransactionHistoryViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return adminTransactionHistoryViewModel;
    }

    /**
     * Gets single admin add game viewModel.
     * @return AdminAddGameViewModel object
     */
    public AdminAddGameViewModel getAdminAddGameViewModel() {
        if (adminAddGameViewModel == null)
            adminAddGameViewModel = new AdminAddGameViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return adminAddGameViewModel;
    }

    /**
     * Gets single admin my account viewModel.
     * @return AdminMyAccountViewModel object
     */
    public AdminMyAccountViewModel getAdminMyAccountViewModel() {
        if (adminMyAccountViewModel == null)
            adminMyAccountViewModel = new AdminMyAccountViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return adminMyAccountViewModel;
    }

    /**
     * Gets single user transaction viewModel.
     * @return UserTransactionHistoryViewModel object
     */
    public UserTransactionHistoryViewModel getUserTransactionHistoryViewModel() {
        if (userTransactionHistoryViewModel == null)
            userTransactionHistoryViewModel = new UserTransactionHistoryViewModel(ModelFactory.getInstance().getClientModelManagerFactory());
        return userTransactionHistoryViewModel;
    }
}

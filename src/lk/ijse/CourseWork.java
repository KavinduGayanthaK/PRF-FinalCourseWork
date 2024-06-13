package lk.ijse;

import java.util.*;

class courseWork {
    static Scanner input = new Scanner(System.in);
    static Random r = new Random();
    static String userName = null;
    static String password = null;
    static String supplierList[][] = new String[0][2];
    static String categoryName[] = new String[0];
    static String[][] itemList = new String[0][6];

    private final static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.equals("Linux")) {
                System.out.print("\033\143");
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void header(String lable) {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|\t\t\t" + lable + " \t\t\t|");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    public static void login() {
        header("LOGIN PAGE\t");
        String uName = getUserInput("User Name : ");
        boolean isMatched = checkUserInput(uName, "username");
        String paw = null;
        while (true) {
            if (isMatched) {
                paw = getUserInput("Password : ");
                break;
            } else {
                userInvalid("user name is invalid.please try again later!");
                uName = getUserInput("User Name : ");
            }
            isMatched = checkUserInput(uName, "username");
        }
        isMatched = checkUserInput(paw, "password");
        while (true) {
            if (isMatched) {
                clearConsole();
                break;
            } else {
                userInvalid("Password is incorrect.please try again!");
                paw = getUserInput("Password : ");
            }
            isMatched = checkUserInput(paw, "password");
        }
    }

    public static String getUserInput(String lable) {
        System.out.print(lable);
        return input.next();
    }

    public static boolean checkUserInput(String input, String type) {
        if (type.equals("username")) {
            return input.equals(userName);
        } else {
            return input.equals(password);
        }
    }

    public static void userInvalid(String lable) {
        System.out.println(lable + "\n");
    }

    public static void stockInside() {
        header("\r\t|WELCOME TO THE IJSE STOCK MANAGEMENT SYSTEM");//meka hadana one
        System.out.println("[1]Change the Credentials\t\t[2]Supplier Manage\n[3]Stock Manage\t\t\t\t[4]Log out\n[5]Exit the System");
        System.out.print("\nEnter an option to continue > ");
        int num = input.nextInt();

        if (num == 1) {
            clearConsole();
            changeCredentials();
        }
        if (num == 4) {
            clearConsole();
            login();
            stockInside();
        }
        if (num == 5) {
            clearConsole();
        }
        if (num == 2) {
            clearConsole();
            supplierManage();
        }
        if (num == 3) {
            clearConsole();
            stockManage();
        }
    }

    public static void changeCredentials() {
        header("Credential Manage");
        System.out.print("Please enter the user name to verify it's you : ");
        String uName = input.next();
        String paw = null;

        boolean isMatched = checkUserInput(uName, "username");
        while (true) {
            if (isMatched) {
                System.out.println("\nHey " + userName);
                System.out.print("Enter your current password : ");
                paw = input.next();
                break;
            } else {
                userInvalid("Invalid user name. try again!");
                System.out.print("Please enter the user name to verify it's you : ");
                uName = input.next();
            }
            isMatched = checkUserInput(uName, "username");
        }
        isMatched = checkUserInput(paw, "password");
        while (true) {
            if (isMatched) {
                break;
            } else {
                userInvalid("Incorrect password.try again!");
                System.out.print("Enter your current password : ");
                paw = input.next();
            }
            isMatched = checkUserInput(paw, "password");

        }
        System.out.print("\nEnter your new password : ");
        password = input.next();
        System.out.print("\nPassword change successfuly!Do you want to go home page (Y/N) : ");
        char ch = input.next().charAt(0);
        if (ch == 'y' || ch == 'Y') {
            clearConsole();
            stockInside();
        } else if (ch == 'n' || ch == 'N') {
            clearConsole();
            changeCredentials();
        } else {
            clearConsole();
            stockInside();
        }
    }

    public static void supplierManage() {
        header("SUPPLIER MANAGE");
        System.out.print("[1] Add Suplier\t\t\t[2] update Supplier\n[3] Delete Supplier\t\t[4] View Supplier\n[5] Search Supplier\t\t[6] Home Page\n");

        System.out.print("\nEnter an option to continue > ");
        int num = input.nextInt();
        if (num == 1) {
            clearConsole();
            addSupplier();
        }
        if (num == 2) {
            clearConsole();
            updateSupplier();
        }
        if (num == 3) {
            clearConsole();
            header("DELETE SUPPLIER");
            deleteSupplier();
        }
        if (num == 4) {
            clearConsole();
            viewSupplier();
        }
        if (num == 5) {
            clearConsole();
            searchSupplier();
        }
        if (num == 6) {
            clearConsole();
            stockInside();
        }
    }

    public static void addSupplier() {
        header("ADD SUPPLIER\t");
        supplierList = grow();
        int i = 0;
        L8:
        while (i < supplierList.length) {
            if (supplierList[i][1] == null) {
                System.out.print("Supplier Id 	: ");
                String id = input.next();

                if (supplierList.length > 1) {
                    boolean isDuplicate = checkId(id);
                    if (isDuplicate) {
                        userInvalid("Already exists.Try another Supplier ID!");
                        continue L8;
                    }
                }
                supplierList[i][0] = id;
                System.out.print("Supplier Name : ");
                supplierList[i][1] = input.next();
                //while(true) {
                System.out.print("Added succesfuly!Do you want to another supplier (Y/N)? ");
                char ch = input.next().charAt(0);

                if (ch == 'Y' || ch == 'y') {
                    clearConsole();
                    addSupplier();
                } else if (ch == 'N' || ch == 'n') {
                    clearConsole();
                    supplierManage();
                } else {
                    clearConsole();
                    supplierManage();
                }
            }
            i++;
        }
    }

    public static String[][] grow() {
        String temp[][] = new String[supplierList.length + 1][2];
        for (int i = 0; i < supplierList.length; i++) {
            for (int j = 0; j < supplierList[i].length; j++) {
                temp[i][j] = supplierList[i][j];
            }
        }
        return temp;
    }

    public static boolean checkId(String id) {
        for (int i = 0; i < supplierList.length; i++) {
            if (supplierList[i][0] != null) {
                if (supplierList[i][0].equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void updateSupplier() {
        header("UPADATE SUPPLIER");
        L4:
        for (int i = 0; i < supplierList.length; ) {
            System.out.print("Supplier ID 	: ");
            String Id = input.next();

            boolean checked = false;
            String duplicateName = null;
            int duplicateIndex = 0;

            for (int k = 0; k < supplierList.length; k++) {
                if (Id.equals(supplierList[k][0])) {
                    checked = true;
                    duplicateName = supplierList[k][1];
                    duplicateIndex = k;
                }
            }
            if (checked) {
                System.out.println("Supplier name : " + duplicateName);
            } else {
                userInvalid("Can;t find Supplier ID.Try again!");
                continue L4;
            }

            System.out.print("Enter the new Suppplier name : ");
            String name = input.next();

            for (int j = 0; j < supplierList[i].length; j++) {
                supplierList[duplicateIndex][1] = name;
            }

            System.out.print("Updated successfuly!Do you want to update another Supplier (Y/N)? ");
            char ch = input.next().charAt(0);
            if (ch == 'Y' || ch == 'y') {
                clearConsole();
                updateSupplier();
            } else if (ch == 'N' || ch == 'n') {
                clearConsole();
                supplierManage();
            }
            i++;
        }
    }

    public static void deleteSupplier() {
        System.out.print("Supplier Id	: ");
        String id = input.next();
        boolean isChecked = false;

        for (int i = 0; i < supplierList.length; i++) {
            if (id.equals(supplierList[i][0])) {
                isChecked = true;
                break;
            }
        }
        if (isChecked) {
            supplierDelete(id);
        } else {
            System.out.println("Cant;t find supplier Id.Try again!");
            deleteSupplier();
        }

        System.out.print("Deleted successfully! Do you want to delete another (Y/N) ?");
        char ch = input.next().charAt(0);
        if (ch == 'Y' || ch == 'y') {
            clearConsole();
            header("DELETE SUPPLIER");
            deleteSupplier();
        } else if (ch == 'N' || ch == 'n') {
            clearConsole();
            supplierManage();
        }
    }

    public static void supplierDelete(String id) {
        String[][] temp = new String[0][2];
        for (int i = 0; i < supplierList.length; i++) {
            if (!id.equals(supplierList[i][0])) {
                temp = growAny(temp.length, 2, temp);
                temp[temp.length - 1][0] = supplierList[i][0];
                temp[temp.length - 1][1] = supplierList[i][1];
            }
        }
        supplierList = temp;
    }

    public static String[][] growAny(int length, int col, String[][] oldTemp) {
        String temp[][] = new String[length + 1][col];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < col; j++) {
                temp[i][j] = oldTemp[i][j];
            }
        }
        return temp;
    }

    public static void viewSupplier() {
        header("    VIEW SUPPLIER ");
        System.out.printf("+--------------+---------------+%n");
        System.out.printf("| %-10s  | %-10s |%n", "SUPPLIER ID", "SUPPLIER NAME");
        System.out.printf("+--------------+---------------+%n");

        for (int i = 0; i < supplierList.length; i++) {
            System.out.printf("| %-12s | %-12s  |%n", supplierList[i][0], supplierList[i][1]);
        }
        System.out.printf("+--------------+---------------+%n");
        L66:
        while (true) {
            System.out.print("\n\nDo you want to go Supplier Manage page (Y/N) ? ");
            char ch = input.next().charAt(0);
            if (ch == 'Y' || ch == 'y') {
                clearConsole();
                supplierManage();
            } else if (ch == 'N' || ch == 'n') {
                clearConsole();
                viewSupplier();
            } else {
                System.out.println("Wronge input! Try again.\n");
                continue L66;
            }
        }
    }

    public static void searchSupplier() {
        header("SEARCH SUPPLIER");
        L15:
        for (int i = 0; i < supplierList.length + 1; ) {
            System.out.print("Suppplier ID	 : ");
            String id = input.next();
            String supName = null;
            boolean checked = false;
            for (int j = 0; j < supplierList.length; j++) {
                if (id.equals(supplierList[j][0])) {
                    checked = true;
                    supName = supplierList[j][1];
                    break;
                }
            }
            if (checked) {
                System.out.println("Supplier name : " + supName);
            } else {
                userInvalid("Can't finf Supplier Id. Try again!");
                continue L15;
            }
            System.out.print("Added successfuly!Do you want to add another find (Y/N)? ");
            char ch = input.next().charAt(0);
            if (ch == 'Y' || ch == 'y') {
                clearConsole();
                searchSupplier();
            } else if (ch == 'N' || ch == 'n') {
                clearConsole();
                supplierManage();
            }
            i++;
        }
    }

    public static void stockManage() {
        header("STOCK MANAGEMENT");
        System.out.println("[1]Manage Item Categories\t[2]Add Item\n[3]Get Items Supplier Wise\t[4]View Item\n[5]Rank Items Per Units Price\t[6]Home Page ");

        System.out.print("\nEnter an option to continue > ");
        int num = input.nextInt();

        if (num == 1) {
            clearConsole();
            manageItemCategory();
        }
        if (num == 2) {
            clearConsole();
            addItem();
        }
        if (num == 3) {
            clearConsole();
            header("Search supplier");
            getItemSupplierWise();
        }
        if (num == 4) {
            clearConsole();
            viewItem();
        }
        if (num == 5) {
            clearConsole();
            rankItemsPerUnitPrice();
        }
        if (num == 6) {
            clearConsole();
            stockInside();
        }
    }

    public static void manageItemCategory() {
        header("MANAGE ITEM CATEGORY");
        System.out.println("[1]Add New Item Category\t[2]Delete Item Category\n[3]Update Item Category\t\t[4]Stock Management");

        System.out.print("\nEnter an option to continue > ");
        int num = input.nextInt();

        if (num == 1) {
            clearConsole();
            addItemCategory();
        }
        if (num == 2) {
            clearConsole();
            header("DELETE ITEM CATEGORY");
            deleteItemCategory();
        }
        if (num == 3) {
            clearConsole();
            updateItemCategory();
        }
        if (num == 4) {
            clearConsole();
            stockManage();
        }

    }

    public static void addItemCategory() {
        header("ADD ITEM CATEGORY");
        categoryName=growCategory();
        for (int i = 0; i < categoryName.length; i++) {
            if(categoryName[i]==null){
                System.out.print("Item category: ");
                categoryName[i]=input.next();
            }
        }
        L97:
        while(true) {
            System.out.print("Added successfuly! Do you want to add another category (Y/N) ? ");
            char ch = input.next().charAt(0);
            if (ch == 'Y' || ch == 'y') {
                clearConsole();
                addItemCategory();
            } else if (ch == 'N' || ch == 'n') {
                clearConsole();
                manageItemCategory();
            } else {
                System.out.println("\nWronge input.Try again!");
                continue L97;
            }
        }
    }

    public static String[] growCategory() {
        String temp[] = new String[categoryName.length + 1];
        for (int i = 0; i < categoryName.length; i++) {
            temp[i] = categoryName[i];
        }
        return temp;
    }

    public static void deleteItemCategory() {

        System.out.print("Item Category : ");
        String category = input.next();
        boolean checked = false;
        for (int i = 0; i < categoryName.length; i++) {
            if (category.equals(categoryName[i])) {
                checked = true;
                break;
            }
        }
        if (checked) {
            deleteCategory(category);
        } else {
            System.out.println("Cant;t find Category Name.Try again!");
            deleteItemCategory();
        }
        System.out.print("Deleted successfully! Do you want to delete another (Y/N) ?");
        char ch = input.next().charAt(0);
        if (ch == 'Y' || ch == 'y') {
            clearConsole();
            header("DELETE ITEM CATEGORY");
            deleteItemCategory();
        } else if (ch == 'N' || ch == 'n') {
            clearConsole();
            manageItemCategory();
        }


    }

    public static void deleteCategory(String category) {
        String temp[] = new String[categoryName.length - 1];
        for (int i = 0; i < categoryName.length; i++) {
            if (!category.equals(categoryName[i])) {
                temp = growCtemp(temp.length, temp);
                temp[temp.length - 1] = categoryName[i];
            }
        }
        categoryName = temp;
    }

    public static String[] growCtemp(int length, String oldTemp[]) {
        String temp[] = new String[length + 1];
        for (int i = 0; i < length; i++) {
            temp[i] = oldTemp[i];
        }
        return temp;
    }

    public static void updateItemCategory() {
        header("UPDATE ITEM CATEGORY");
        L25:
        for (int i = 0; i < categoryName.length; ) {
            System.out.print("Enter current category name : ");
            String name = input.next();

            boolean isChecked = false;
            for (int j = 0; j < categoryName.length; j++) {
                if (name.equals(categoryName[j])) {
                    isChecked = true;
                    break;
                }
            }
            if (isChecked) {
                System.out.print("Enter new category name : ");
                String newName = input.next();

                categoryName[i] = newName;
            } else {
                System.out.println("Can't find category. Try again!\n");
                continue L25;
            }
            System.out.println("Update succussfuly!Do yo want to update another category (Y/N)? ");
            char ch = input.next().charAt(0);
            if (ch == 'Y' || ch == 'y') {
                clearConsole();
                updateItemCategory();
            } else if (ch == 'N' || ch == 'n') {
                clearConsole();
                manageItemCategory();
            }
            i++;
        }
    }

    public static void addItem() {
        header("\tADD ITEM");

        if (categoryName.length == 0) {
            System.out.println("OOPS! It seems that you don't have any item categories in the system.");
            System.out.print("Do you want to add a new item category?(Y/N)");
            char ch = input.next().charAt(0);

            if (ch == 'y' || ch == 'Y') {
                clearConsole();
                addItemCategory();
            } else if (ch == 'n' || ch == 'N') {
                clearConsole();
                stockManage();
            }
        }
        if (supplierList.length == 0) {
            System.out.println("OOPS! It seems that you don't have any suppliers in the system.");
            System.out.print("Do you want to add a new supplier? (Y/N)");
            char ch = input.next().charAt(0);

            if (ch == 'Y' || ch == 'y') {
                clearConsole();
                addSupplier();
            } else if (ch == 'N' || ch == 'n') {
                clearConsole();
                stockManage();
            }
        }


        L90:
        while (true) {
            System.out.print("Item code 	: ");
            String code = input.next();

            for (int j = 0; j < itemList.length; j++) {
                if (code.equals(itemList[j][1])) {
                    userInvalid("Already added. Try another item code!");
                    continue L90;
                }
            }
            supplierListView();

            itemList = growItemList();

            System.out.print("Enter the supplier number > ");
            int num = input.nextInt();

            viewItemCategory();

            System.out.print("Enter the category number > ");
            int categoryNum = input.nextInt();

            System.out.print("\nDescription : ");
            String description = input.next();

            System.out.print("Unite price : ");
            double price = input.nextDouble();

            System.out.print("Qty on hand : ");
            int quantity = input.nextInt();

            for (int i = 0; i < itemList.length; i++)
            {
                if(itemList[i][0]==null){
                    itemList[i][0] = supplierList[num - 1][0];
                    itemList[i][1] = code;
                    itemList[i][2] = description;
                    itemList[i][3] = String.valueOf(price);
                    itemList[i][4] = String.valueOf(quantity);
                    itemList[i][5] = categoryName[categoryNum - 1];
                }
            }

            System.out.print("Added successfully! Do you want to add another Item (Y/N)?");
            char add = input.next().charAt(0);
            if (add == 'Y' || add == 'y') {
                clearConsole();
                continue L90;
            } else if (add == 'N' || add == 'n') {
                clearConsole();
                stockManage();
                break;
            }
        }
    }

    public static String[][] growItemList() {
        String[][] temp = new String[itemList.length + 1][6];
        for (int i = 0; i < itemList.length; i++) {
            for (int j = 0; j < itemList[i].length; j++) {
                temp[i][j] = itemList[i][j];
            }
        }
        return temp;
    }

    public static void supplierListView() {
        System.out.println("Suppliers list:");
        System.out.printf("+--------------+---------------+--------------+%n");
        System.out.printf("| %-12s | %-12s  | %-12s|%n", "#", "SUPPLIER ID", "SUPPLIER NAME");
        System.out.printf("+--------------+---------------+--------------+%n");

        for (int i = 0; i < supplierList.length; i++) {
            System.out.printf("| %-12s | %-12s  | %-12s |%n", i + 1, supplierList[i][0], supplierList[i][1]);
        }
        System.out.printf("+--------------+---------------+--------------+%n");
    }

    public static void viewItemCategory() {
        System.out.println("\nItem Categories:");
        System.out.printf("+--------------+---------------+%n");
        System.out.printf("| %-12s | %-12s |%n", "#", "CATEGORY NAME");
        System.out.printf("+--------------+---------------+%n");

        for (int i = 0; i < categoryName.length; i++) {
            System.out.printf("| %-12s | %-12s  |%n", i + 1, categoryName[i]);
        }
        System.out.printf("+--------------+---------------+%n");
    }

    public static void getItemSupplierWise() {
        System.out.print("Enter Supplier Id : ");
        String id = input.next();

        int check=0;

        for (int i = 0; i < supplierList.length; i++)
        {
            if(id.equals(supplierList[i][0])) {
                check++;
            }
        }

        if(check>0) {
            for (int i = 0; i < supplierList.length; i++) {
                if (id.equals(supplierList[i][0])) {
                    System.out.print("Supplier Name : " + supplierList[i][1] + "\n");

                }
            }
            System.out.printf("+--------------+---------------+---------------+--------------+---------------+%n");
            System.out.printf("| %-12s | %-12s  | %-12s  | %-12s | %-12s  |%n", "ITEM CODE", "DESCRIPTION", "UNI PRICE", "QTY ON HAND", "CATEGORY");
            System.out.printf("+--------------+---------------+---------------+--------------+---------------+%n");

            for (int j = 0; j < itemList.length; j++) {
                if (id.equals(itemList[j][0])) {
                    System.out.printf("| %-12s | %-12s  | %-12s  | %-12s | %-12s  |%n", itemList[j][1], itemList[j][2], itemList[j][3], itemList[j][4], itemList[j][5]);
                }
            }
            System.out.printf("+--------------+---------------+---------------+--------------+---------------+%n");
            L34:
            while (true) {
                System.out.print("Search successfully! Do you want to another search?(Y/N)");
                char ch = input.next().charAt(0);
                if (ch == 'Y' || ch == 'y') {
                    clearConsole();
                    getItemSupplierWise();
                } else if (ch == 'N' || ch == 'n') {
                    clearConsole();
                    stockManage();
                } else {
                    System.out.println("\nWronge input.Try again!");
                    continue L34;
                }
            }
        }else{
            System.out.println("Wrong Input Try again:!");
            getItemSupplierWise();
        }
    }

    public static void viewItem() {
        header("\tVIEW ITEM");
        for (int i = 0; i < categoryName.length; i++) {
            System.out.println(categoryName[i]);
            System.out.printf("+--------------+---------------+---------------+--------------+---------------+%n");
            System.out.printf("| %-12s | %-12s  | %-12s  | %-12s | %-12s  |%n", "SID", " CODE", "DESC", "PRICE", "QTY");
            System.out.printf("+--------------+---------------+---------------+--------------+---------------+%n");

            for (int j = 0; j < itemList.length; j++) {
                if (categoryName[i].equals(itemList[j][5])) {
                    System.out.printf("| %-12s | %-12s  | %-12s  | %-12s | %-12s  |%n", itemList[j][0], itemList[j][1], itemList[j][2], itemList[j][3], itemList[j][4]);
                }
            }
            System.out.printf("+--------------+---------------+---------------+--------------+---------------+%n");
        }
        L34:
        while (true) {
            System.out.print("Do you want to go stock manage page?(Y/N)");
            char ch = input.next().charAt(0);
            if (ch == 'Y' || ch == 'y') {
                clearConsole();
                stockManage();
            } else if (ch == 'N' || ch == 'n') {
                clearConsole();
                viewItem();
            } else {
                System.out.println("Wronge input! Try again.\n");
                continue L34;
            }
        }
    }

    public static void rankItemsPerUnitPrice() {
        header("RANKED UNIT PRICE");
        String[] temp = new String[itemList[1].length];
        System.out.printf("+--------------+---------------+---------------+--------------+---------------+--------------+%n");
        System.out.printf("| %-12s | %-12s  | %-12s  | %-12s | %-12s  | %-12s |%n", "SID", " CODE", "DESC", "PRICE", "QTY", "CAT");
        System.out.printf("+--------------+---------------+---------------+--------------+---------------+--------------+%n");
        for (int i = 0; i < itemList.length; i++) {
            for (int j = 0; j < itemList.length-1; j++) {
                if (Double.parseDouble(itemList[j][3]) > Double.parseDouble(itemList[j + 1][3])) {
                    for (int k = 0; k < itemList.length; k++) {
                        temp[k] = itemList[j][k];
                        itemList[j][k] = itemList[j + 1][k];
                        itemList[j + 1][k] = temp[k];
                    }
                }
            }
        }
        for (int i = 0; i < itemList.length; i++) {
            System.out.printf("| %-12s | %-12s  | %-12s  | %-12s | %-12s  | %-12s |%n", itemList[i][0], itemList[i][1], itemList[i][2], itemList[i][3], itemList[i][4], itemList[i][5]);
        }

        System.out.printf("+--------------+---------------+---------------+--------------+---------------+--------------+%n");
        L34:
        while (true) {
            System.out.print("Do you want to go stock manage page?(Y/N)");
            char ch = input.next().charAt(0);
            if (ch == 'Y' || ch == 'y') {
                clearConsole();
                stockManage();
            } else if (ch == 'N' || ch == 'n') {
                clearConsole();
                rankItemsPerUnitPrice();
            } else {
                System.out.println("Wronge input.Try again!");
                continue L34;
            }
        }
    }

    public static void main(String args[]) {
        header("WELCOME TO THE SING UP");
        userName = getUserInput("Enter user name : ");
        password = getUserInput("Enter Password : ");
        clearConsole();

        login();
        stockInside();
    }
}


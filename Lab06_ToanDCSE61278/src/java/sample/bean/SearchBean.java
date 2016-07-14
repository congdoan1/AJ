package sample.bean;

import java.io.Serializable;
import java.util.List;
import sample.account.AccountDAO;
import sample.account.AccountDTO;

/**
 *
 * @author Suzy
 */
public class SearchBean implements Serializable {
    private String search;
    private List<AccountDTO> list;

    public SearchBean() {
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<AccountDTO> getList() {
        return list;
    }

    public void setList(List<AccountDTO> list) {
        this.list = list;
    }

    public void searchLikeLastname() {
        if (search == null) {
            search = "";
        }
        AccountDAO dao = new AccountDAO();
        list = dao.searchLikeLastname(search);
    }
    
    public AccountDTO searchByUsername() {
        AccountDAO dao = new AccountDAO();
        AccountDTO dto = dao.searchByUser(search);
        return dto;
    }
}

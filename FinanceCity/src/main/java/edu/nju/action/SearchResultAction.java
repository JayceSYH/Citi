package edu.nju.action;

import org.springframework.stereotype.Controller;

/**
 * Created by Hermit on 16/9/3.
 */
@Controller
public class SearchResultAction extends BaseAction {

    @SuppressWarnings("unchecked")
    public String search() {

        return SUCCESS;
    }
}

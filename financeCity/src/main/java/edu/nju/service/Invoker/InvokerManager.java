package edu.nju.service.Invoker;

import edu.nju.service.BaseService.BaseService;
import edu.nju.service.ExceptionsAndError.DuplicateFunctionNameException;
import edu.nju.service.ExceptionsAndError.InvalidAPINameException;
import edu.nju.service.ExceptionsAndError.InvalidParametersException;

import java.util.List;

/**
 * Created by dell on 2016/8/13.
 */
public interface InvokerManager {
    void addInvoker(String name, BaseService baseService) throws DuplicateFunctionNameException;
    Object invokeAPI(String apiName, List<Object> param) throws InvalidAPINameException, InvalidParametersException;
    void loadService(BaseService service, APIFilter apiFilter) throws DuplicateFunctionNameException;
}

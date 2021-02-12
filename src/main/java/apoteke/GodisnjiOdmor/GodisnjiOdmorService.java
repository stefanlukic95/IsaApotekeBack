package apoteke.GodisnjiOdmor;

import java.util.List;

public interface GodisnjiOdmorService {

    List<GodisnjiOdmor> findAll();
    GodisnjiOdmor findOne(Integer id);
    GodisnjiOdmor create(GodisnjiOdmor godisnjiOdmor) throws Exception;
    void delete(Integer id);
    GodisnjiOdmor update(GodisnjiOdmor godisnjiOdmor) throws Exception;
}

package apoteke.GodisnjiOdmor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GodisnjiOdmorServiceImpl implements GodisnjiOdmorService{

    @Autowired
    private GodisnjiOdmorRepository godisnjiOdmorRepository;
    //svi
    @Override
    public List<GodisnjiOdmor> findAll() {
        return godisnjiOdmorRepository.findAll();
    }

    //po id
    @Override
    public GodisnjiOdmor findOne(Integer id) {
        return godisnjiOdmorRepository.findById(id).orElse(null);
    }

    @Override
    public GodisnjiOdmor create(GodisnjiOdmor godisnjiOdmor) throws Exception {
        GodisnjiOdmor  savedGodisnjiOdmor = this.godisnjiOdmorRepository.save(godisnjiOdmor);

        return savedGodisnjiOdmor;
    }

    @Override
    public void delete(Integer id) {
        this.godisnjiOdmorRepository.deleteById(id);}

    @Override
    public GodisnjiOdmor update(GodisnjiOdmor g) {

        return godisnjiOdmorRepository.save(g);
    }

}

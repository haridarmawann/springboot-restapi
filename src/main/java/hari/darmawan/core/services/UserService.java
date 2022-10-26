package hari.darmawan.core.services;

import hari.darmawan.core.models.entities.User;
import hari.darmawan.core.models.repos.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserRepo userRepo;

    // untuk mencari semua data user
    public List<User> findAll(){
        return userRepo.findAll();
    }

    public User save(User user){
        return userRepo.save(user);
    }

    public Optional<User> findByid(String id){
        return userRepo.findById(id);
    }

    public void Remove(String id){
        userRepo.deleteById(id);
    }
}

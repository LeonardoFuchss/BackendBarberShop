package com.backend.reservetion.Barbershop.service;

import com.backend.reservetion.Barbershop.domain.User;
import com.backend.reservetion.Barbershop.dtos.ActivityDTOs;
import com.backend.reservetion.Barbershop.domain.Activity;
import com.backend.reservetion.Barbershop.exceptions.ActivityException;
import com.backend.reservetion.Barbershop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.backend.reservetion.Barbershop.repositories.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private UserRepository userRepository;


    public Activity createdActivity (ActivityDTOs activityDTOs) throws ActivityException {
try {
    Activity newActivity = new Activity(activityDTOs);
    Long userId = activityDTOs.user().getId(); // Armazenando o Id que quero procurar em uma variável
    Optional<User> userOptional = userRepository.findById(userId); // Usando o repository para procurar por id
    if (userOptional.isPresent()){
        User user = userOptional.get();
        newActivity.setUserName(user.getFirstName() + " " + user.getLastName());
    }
    switch (newActivity.getActivityType()) { // Casos para quando criarmos um usuário.
        case LOWFADE:
            newActivity.setValueType(30.0);
            break;
        case MIDFADE:
            newActivity.setValueType(25.0);
            break;
        case AMERICAN:
            newActivity.setValueType(40.0);
            break;
        case HIGHFADE:
            newActivity.setValueType(25);
        default:

            break;
    }
    activityRepository.save(newActivity); // chama o método save para salvar no banco de dados
    return newActivity;
} catch (Exception e) {
    throw new ActivityException(" Não foi possivel criar um usuário. Data e horário já utilizados! ");
}
    }
    public List<Activity> getAll() throws ActivityException {
        try {
            return activityRepository.findAll(); // Busca todas as atividades marcadas.

        } catch (Exception e) {
            throw new ActivityException("Não foi possivel buscar os usuários.");
        }
    }
    public Activity activityById(Long id) throws ActivityException {// Busca as atividades por Id
        try {
            return activityRepository.findById(id).get();
        } catch (Exception e) {
            throw new ActivityException("Não foi possivel buscar usuário por id");
        }
    }
    public void deleteById(Long id) throws ActivityException { // Deleta as atividades por id
        try {
            activityRepository.deleteById(id);
        } catch (Exception e) {
            throw new ActivityException("Atividade não encontrada");
        }
    }
    public Activity update(Long id, ActivityDTOs activityDTOs) throws ActivityException {  // Atualiza as atividades por id, definindo novos valores a elas com o setter
        try {
            Activity updateActivity = activityById(id);
            updateActivity.setActivityType(activityDTOs.activityType());
            updateActivity.setDateTime(activityDTOs.dateTime());

            switch (updateActivity.getActivityType()) { // Casos para quando criarmos um usuário.
                case LOWFADE:
                    updateActivity.setValueType(30.0);
                    break;
                case MIDFADE:
                    updateActivity.setValueType(25.0);
                    break;
                case AMERICAN:
                    updateActivity.setValueType(40.0);
                    break;
                case HIGHFADE:
                    updateActivity.setValueType(25);
                default:
                    // Lidar com outros tipos de atividade, se necessário
                    break;
            }
            activityRepository.save(updateActivity);

            return updateActivity;
        } catch (Exception e) {
            throw new ActivityException("Atividade não encontrada.");
        }
    }
}

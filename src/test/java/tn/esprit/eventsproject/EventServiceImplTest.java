package tn.esprit.eventsproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EventServiceImplTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addParticipantTest() {
        // Création d'un participant fictif
        Participant participant = new Participant();
        participant.setIdPart(1);
        participant.setNom("John");
        participant.setPrenom("Doe");

        // Simulation du comportement du repository
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        // Appel de la méthode à tester
        Participant result = eventServices.addParticipant(participant);

        // Vérification que le participant n'est pas null
        assertNotNull(result);

        // Vérification que le participant a été sauvegardé
        verify(participantRepository).save(participant);

        // Vérification des attributs
        assertEquals("John", result.getNom());
        assertEquals("Doe", result.getPrenom());
    }
}

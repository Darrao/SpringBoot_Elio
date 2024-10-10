package com.example.event_management;

import com.example.event_management.entity.Event;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        // Initialisation des mocks
        MockitoAnnotations.openMocks(this);
        System.out.println("Mocks initialisés");
    }

    @Test
    public void testAddEvent() {
        Event event = new Event();
        event.setTitle("Miaou");
        event.setDescription("Quel beau chat!");
        event.setEventDate(LocalDate.now());

        System.out.println("Avant appel à eventService.addEvent()");
        System.out.println("Événement créé : " + event);

        when(eventRepository.save(event)).thenReturn(event);

        Event savedEvent = eventService.addEvent(event);

        System.out.println("Après appel à eventService.addEvent()");
        System.out.println("Événement sauvegardé : " + savedEvent);

        assertThat(savedEvent.getTitle()).isEqualTo("Miaou");
        assertThat(savedEvent.getDescription()).isEqualTo("Quel beau chat!");

        System.out.println("Test réussi");
    }
}
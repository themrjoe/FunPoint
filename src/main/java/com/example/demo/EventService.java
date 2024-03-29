package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;

    private CategoryRepository categoryRepository;

    @Autowired
    public void setEventRepository(EventRepository repository) {
        this.eventRepository = repository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository repository) {
        this.categoryRepository = repository;
    }

        public void saveEvent(Event event) {
            Category c = categoryRepository.getCategoryByTitle(event.getCategoryTitle());
            event.setCategory(c);
            eventRepository.save(event);
            c.addEvent(event);
            categoryRepository.save(c);
        }

        public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }

    public void updateEvent(int id, String title, int category_id, String address, Date event_date, Time event_time, double price, String description) {
        Event e = eventRepository.getOne(id);
        e.setAddress(address);
        e.setDescription(description);
        e.setEvent_date(event_date);
        e.setEvent_time(event_time);
        e.setPrice(price);
        e.setTitle(title);
        Category c = categoryRepository.getOne(category_id);
        e.setCategory(c);
        eventRepository.save(e);
    }
}

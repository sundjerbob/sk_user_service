package raf.sk.sk_user_service.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import raf.sk.sk_user_service.inter_service_comunication.BookedWorkoutsIncrementDto;
import raf.sk.sk_user_service.listener.helper.MessageHelper;
import raf.sk.sk_user_service.service.api.ClientServiceApi;

import javax.jms.JMSException;
import javax.jms.Message;
@Component
public class BookedWorkoutsIncrementListener {

    private final MessageHelper messageHelper;
    private final ClientServiceApi clientService;

    public BookedWorkoutsIncrementListener(MessageHelper messageHelper, ClientServiceApi clientService) {
        this.messageHelper = messageHelper;
        this.clientService = clientService;
    }


    @JmsListener(destination = "${destination.incrementBookedWorkouts}", concurrency = "5-10")
    public void addOrder(Message message) throws JMSException {
        BookedWorkoutsIncrementDto bookedWorkoutsIncrementDto = messageHelper.getMessage(message, BookedWorkoutsIncrementDto.class);
        clientService.incrementBookedWorkouts(bookedWorkoutsIncrementDto.getClientId(), bookedWorkoutsIncrementDto.getGymName());
    }
}

package com.kamindo.propertymanagement;

import com.kamindo.propertymanagement.property.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
public class PropertyCommandHandlerTests {
    @Mock
    private PropertyRepository propertyRepositoryMock;

    @Mock
    private ApplicationEventPublisher eventPublisherMock;

    @Test
    public void testHandleCreatePropertyCommand() {
        // Arrange
        PropertyCommandHandler commandHandler = new PropertyCommandHandler(propertyRepositoryMock, eventPublisherMock);
        String propertyName = "Sample Property";
        Address address = new Address("123 Main Street", "Sample City", "Sample State", "12345", "Sample Country");
        CreatePropertyCommand command = new CreatePropertyCommand();
        command.setName(propertyName);
        command.setAddress(address);

        // Act
        commandHandler.handle(command);

        // Assert
        Mockito.verify(propertyRepositoryMock, Mockito.times(1)).save(Mockito.any(Property.class));
        Mockito.verify(eventPublisherMock, Mockito.times(1)).publishEvent(Mockito.any(PropertyCreatedEvent.class));
    }
}

package sopt.sopkathon37.domain.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.sopkathon37.domain.booking.repository.BookingRepository;
import sopt.sopkathon37.domain.booking.service.dto.BookingCommand;
import sopt.sopkathon37.domain.helper.repository.Helper;
import sopt.sopkathon37.domain.helper.service.HelperService;
import sopt.sopkathon37.domain.serviceoffer.repository.ServiceOffer;
import sopt.sopkathon37.domain.serviceoffer.service.ServiceOfferService;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final HelperService helperService;
    private final ServiceOfferService serviceOfferService;
    private final BookingRepository bookingRepository;

    @Transactional
    public void booking(BookingCommand command) {
        Helper helper = helperService.getHelperById(command.helperId());
        ServiceOffer serviceOffer = serviceOfferService.getServiceOfferById(
            command.serviceOfferId());

        bookingRepository.save(command.toBooking(helper, serviceOffer));
    }
}

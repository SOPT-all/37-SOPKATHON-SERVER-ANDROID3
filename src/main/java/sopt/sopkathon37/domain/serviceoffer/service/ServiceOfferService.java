package sopt.sopkathon37.domain.serviceoffer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.sopkathon37.domain.serviceoffer.repository.ServiceOffer;
import sopt.sopkathon37.domain.serviceoffer.repository.ServiceOfferRepository;
import sopt.sopkathon37.global.exception.ServerException;
import sopt.sopkathon37.global.message.ErrorMessage;

@Service
@RequiredArgsConstructor
public class ServiceOfferService {

    private final ServiceOfferRepository serviceOfferRepository;

    @Transactional(readOnly = true)
    public ServiceOffer getServiceOfferById(long serviceOfferId) {
        return serviceOfferRepository.findById(serviceOfferId)
            .orElseThrow(
                () -> new ServerException(ErrorMessage.NOT_FOUND)
            );
    }
}

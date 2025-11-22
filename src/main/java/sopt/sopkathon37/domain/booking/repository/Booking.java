package sopt.sopkathon37.domain.booking.repository;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sopt.sopkathon37.domain.helper.repository.Helper;
import sopt.sopkathon37.domain.serviceoffer.repository.ServiceOffer;

@Table(name = "bookings")
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_offer_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ServiceOffer serviceOffer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "helper_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Helper helper;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String specialRequests;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;

    @Column(nullable = false)
    private BigDecimal estimatedFee;

    @Column(nullable = false)
    private BigDecimal reservationFee;

    @Column(nullable = false)
    private BigDecimal totalEstimated;

    @Column(nullable = false)
    private String visitDate;

    @Column(nullable = false)
    private String locate;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    private Booking(ServiceOffer serviceOffer, Helper helper, String specialRequests,
        String startTime,
        String endTime,
        BigDecimal estimatedFee, BigDecimal reservationFee, BigDecimal totalEstimated,
        String visitDate,
        String locate) {
        this.serviceOffer = serviceOffer;
        this.helper = helper;
        this.specialRequests = specialRequests;
        this.startTime = startTime;
        this.endTime = endTime;
        this.estimatedFee = estimatedFee;
        this.reservationFee = reservationFee;
        this.totalEstimated = totalEstimated;
        this.visitDate = visitDate;
        this.locate = locate;
    }

    public static Booking create(ServiceOffer serviceOffer, Helper helper, String specialRequests,
        String startTime,
        String endTime,
        BigDecimal estimatedFee, BigDecimal reservationFee, BigDecimal totalEstimated,
        String visitDate,
        String locate) {
        return Booking.builder()
            .serviceOffer(serviceOffer)
            .helper(helper)
            .specialRequests(specialRequests)
            .startTime(startTime)
            .endTime(endTime)
            .estimatedFee(estimatedFee)
            .reservationFee(reservationFee)
            .totalEstimated(totalEstimated)
            .visitDate(visitDate)
            .locate(locate)
            .build();
    }
}

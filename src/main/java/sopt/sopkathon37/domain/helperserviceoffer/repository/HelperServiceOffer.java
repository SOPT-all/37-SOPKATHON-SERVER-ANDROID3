package sopt.sopkathon37.domain.helperserviceoffer.repository;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sopt.sopkathon37.domain.helper.repository.Helper;
import sopt.sopkathon37.domain.serviceoffer.repository.ServiceOffer;

@Table(name = "helper_service_offers")
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HelperServiceOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "helper_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Helper helper;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_offer_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ServiceOffer serviceOffer;

    @Column(nullable = false)
    private String timeTaken;
}

package sopt.sopkathon37.domain.helper.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sopt.sopkathon37.domain.helperserviceoffer.repository.HelperServiceOffer;

@Table(name = "helpers")
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Helper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String about;

    @Column(nullable = false)
    private float rate;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String experience;

    @Column(nullable = false)
    private String availability;

    @Column(nullable = false)
    private BigDecimal hourlyRate;

    @Column(nullable = false)
    private String distance;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String imageURL;

    @Column(nullable = false)
    private boolean verified;

    @OneToMany(mappedBy = "helper")
    private List<HelperServiceOffer> offers = new ArrayList<>();
}

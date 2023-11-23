package host.luke.knowbaby.repository;

import host.luke.knowbaby.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

  Merchant findByUsername(String username);
}

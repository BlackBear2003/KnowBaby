package host.luke.knowbaby.service;

import cn.hutool.core.bean.BeanUtil;
import host.luke.knowbaby.entity.Customer;
import host.luke.knowbaby.entity.Merchant;
import host.luke.knowbaby.entity.vo.CustomerVo;
import host.luke.knowbaby.repository.CustomerRepository;
import host.luke.knowbaby.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired
  CustomerRepository customerRepository;
  @Autowired
  QiniuService qiniuService;

  public Customer findByUsername(String username) {
    return customerRepository.findByUsername(username);
  }

  public Customer findById(long id) {
    return customerRepository.findById(id).orElse(new Customer());
  }

  public Customer save(Customer customer) {
    return customerRepository.save(customer);
  }

  public Customer save(CustomerVo vo) {
    Customer customer = new Customer();
    BeanUtil.copyProperties(vo, customer);
    customer.setAvatarUrl(qiniuService.uploadImg(vo.getAvatarBase64()));
    return customerRepository.save(customer);
  }

}

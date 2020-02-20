package epamTask.task33;
@Controller
@RequiredArgsConstructor


	public class layeredArchitecture 
	{
		private final layeredArchitecture customerService;
		@PostMapping(value = "/customers/add-money")
		public @ResponseBody CustomerAddMoneyDTO addMoney(@RequestBody CustomerAddMoneyDTO dto) 
		{
			return customerService.addFundsToCustomer(dto);
		}
	}
	 class Objects 
	 {
			class CustomerAddMoneyDTO 
			{
				  public String uname;
				  public Integer amount;
				  
			}

			class Customer {
			  
			  public String uname;
			  public String pword;
			  public String amount;
			  
			}

			@Entity
			@Table(name="CUSTOMER")
			class CustomerEntity {
			  
			  @Id
			  @GeneratedValue(strategy=GenerationType.AUTO)
			  public Long id;
			   
			  @Column(name="USERNAME")
			  public String username;
			  
			  @Column(name="PASSWORD")
			  public String password;
			  
			  @Column(name="MONEY_AMOUNT")
			  public Integer moneyAmount;
			  
				}

		}
	 	@Component
		class CustomerMapper {
		  
		  public CustomerAddMoneyDto mapToDto(CustomerEntity customerEntity) {
		    CustomerAddMoneyDto dto = new CustomerAddMoneyDto();
		    dto.username = customerEntity.getUsername();
		    dto.moneyAmount = customerEntity.getMoneyAmount();
		    return dto;
		  }
		  
		}

		@Service
		@AllArgsConstructor
		class CustomerService 
		{
		  
			  private final customer customerRepository;
			  private final CustomerMapper customerMapper;
		  
		  public CustomerAddMoneyDto addFundsToCustomer(CustomerAddMoneyDto dto) 
		  {
		  
		    CustomerEntity customerEntity = customerRepository.findByUsername(dto.getUsername());
		    customerEntity.moneyAmount += dto.getMoneyAmount();
		    return customerMapper.mapToDto(customerRepository.save(customerEntity));
		  }
		  
		}


}

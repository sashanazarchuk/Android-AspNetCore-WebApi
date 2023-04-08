using AutoMapper;
using BusinessLogic.Interfaces;
using Data.Data;
using Data.Model;
using Server.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BusinessLogic.Services
{
    public class ProductService:IProductService
    {
        private readonly ShopDbContext context;
        private readonly IMapper mapper;
        public ProductService(ShopDbContext context, IMapper mapper)
        {
            this.context = context;
            this.mapper = mapper;
        }

        public IEnumerable<ProductDTO> GetAll()
        {
            var products = context.Products.ToList();
            return mapper.Map<IEnumerable<ProductDTO>>(products);
        }

        public ProductDTO? GetById(int id)
        {
            var products = context.Products.Find(id);

            if (products == null) return null;
            return mapper.Map<ProductDTO>(products);
        }
        public void Create(ProductDTO products)
        {
            context.Products.Add(mapper.Map<Product>(products));
            context.SaveChanges();
        }
    }
}

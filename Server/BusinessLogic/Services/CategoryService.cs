using AutoMapper;
using BusinessLogic.DTOs;
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
    public class CategoryService : ICategoriesService
    {
        private readonly ShopDbContext context;
        private readonly IMapper mapper;

        public CategoryService(ShopDbContext context, IMapper mapper)
        {
            this.context = context;
            this.mapper = mapper;
        }

        public void Create(CategoryDTO category)
        {
            context.Categories.Add(mapper.Map<Categories>(category));
            context.SaveChanges();
        }

        public void Delete(int id)
        {
            var category =context.Categories.Find(id);
            if (category == null) return;
            context.Categories.Remove(category);
            context.SaveChanges();
        }

        public IEnumerable<CategoryDTO> GetAll()
        {
            var category = context.Categories.ToList();
            return mapper.Map<IEnumerable<CategoryDTO>>(category);
        }

        public CategoryDTO? GetById(int id)
        {
            var category = context.Categories.Find(id);

            if (category == null) return null;
            return mapper.Map<CategoryDTO>(category);
        }
    }
}

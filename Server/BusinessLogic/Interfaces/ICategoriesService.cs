using BusinessLogic.DTOs;
using Server.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BusinessLogic.Interfaces
{
    public interface ICategoriesService
    {
        IEnumerable<CategoryDTO> GetAll();
        CategoryDTO? GetById(int id);
        void Create(CategoryDTO category);
        void Delete(int id);
    }
}

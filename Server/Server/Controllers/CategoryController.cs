using BusinessLogic.DTOs;
using BusinessLogic.Interfaces;
using BusinessLogic.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Server.Models;

namespace Server.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CategoryController : ControllerBase
    {
        private readonly ICategoriesService categoryservice;
        public CategoryController(ICategoriesService categoryservice)
        {
            this.categoryservice = categoryservice;
        }


        [HttpGet("GetAll")]
        public IActionResult GetAll()
        {
            return Ok(categoryservice.GetAll());
        }


        [HttpGet("get/{id}")]
        public IActionResult Get([FromRoute] int id)
        {
            var category = categoryservice.GetById(id);

            if (category == null) return NotFound();

            return Ok(category);
        }


        [HttpPost]
        public IActionResult Create([FromBody] CategoryDTO category)
        {
            categoryservice.Create(category);

            return Ok();
        }

        [HttpDelete("{id}")]
        public IActionResult Delete([FromRoute] int id)
        {
            var category = categoryservice.GetById(id);

            if (category == null) return NotFound();

            categoryservice.Delete(id);

            return Ok();
        }

        [HttpPut]
        public IActionResult Edit([FromBody] CategoryDTO category)
        {
            categoryservice.Edit(category);

            return Ok();
        }
    }
}

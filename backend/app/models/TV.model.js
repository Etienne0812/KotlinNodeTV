module.exports = (sequelize, Sequelize) => {
    const TV = sequelize.define("tv", {
      brand: {
        type: Sequelize.STRING
      },
      model: {
        type: Sequelize.STRING
      }, 
      price: {
        type: Sequelize.STRING
      }
    }, { timestamps: false});
  
    return TV;
  };

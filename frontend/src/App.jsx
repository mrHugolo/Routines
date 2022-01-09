import { BrowserRouter as Router, Route, Switch } from "react-router-dom"

import { Home } from "./pages/Home"
import { Statistics } from "./pages/Statistics"

function App() {
 
  return (
    <div>
      <Router>
        <Switch>
          <Route exact path="/" component={Home} />
          <Route exact path="/stat" component={Statistics}/>
        </Switch>
      </Router>
    </div>
  )
}

export default App
